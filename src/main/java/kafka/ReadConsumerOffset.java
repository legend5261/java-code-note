package kafka;

import kafka.common.OffsetAndMetadata;
import kafka.coordinator.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.nio.ByteBuffer;
import java.util.*;

public class ReadConsumerOffset {
    public static final String Brokers = "10.9.122.159:9092,10.9.126.86:9092,10.9.166.209:9092,10.9.130.39:9092";
    //public static final String Brokers = "192.168.38.58:9092,192.168.38.57:9092,192.168.38.59:9092";
    public static final String GroupID = "group_offset_2";


    public static void main(String[] args) throws Exception {
        Consumer<byte[], byte[]> consumer = CreateKafkaConsumer();
        consumer.subscribe(Collections.singletonList("__consumer_offsets"));
        System.out.println("Subscribed to topic : __consumer_offsets");
        System.out.println(consumer.metrics());

        while (true) {
            ConsumerRecords<byte[], byte[]> records = consumer.poll(100);

            for (ConsumerRecord<byte[], byte[]> record : records) {
                // 这里直接将record的全部信息写到System.out打印流中
                // GroupMetadataManager.OffsetsMessageFormatter formatter = new GroupMetadataManager.OffsetsMessageFormatter();
                // formatter.writeTo(record, System.out);

                // 对record的key进行解析，注意这里的key有两种OffsetKey和GroupMetaDataKey
                // GroupMetaDataKey中只有消费者组ID信息，OffsetKey中还包含了消费的topic信息
                BaseKey key = GroupMetadataManager.readMessageKey(ByteBuffer.wrap(record.key()));
                // System.out.println(key.key());

                if (key instanceof OffsetKey) {
                    GroupTopicPartition partition = (GroupTopicPartition) key.key();
                    String topic = partition.topicPartition().topic();
                    int prt = partition.topicPartition().partition();
                    String group = partition.group();
                    if (topic.equals("app_dotting_log") == false) {
                        continue;
                    }
                    System.out.println("Topic: " + topic + "\t Partition: " + prt);
                    System.out.println("Group: " + group);
                    System.out.println(key.toString());

                    //对record的value进行解析
                    byte[] value = record.value();
                    if (value != null) {
                        OffsetAndMetadata om = GroupMetadataManager.readOffsetMessageValue(ByteBuffer.wrap(record.value()));
                        System.out.println("OffsetAndMetadata: " + om.toString());
                    }

                } else if (key instanceof GroupMetadataKey) {
                    System.out.println("GroupMetadataKey: " + key.key());
                    byte[] value = record.value();
                    if (value != null) {
                        GroupMetadata groupMetadata = GroupMetadataManager.readGroupMessageValue(key.key().toString(), ByteBuffer.wrap(record.value()));
                        System.out.println("GroupMetadata: " + groupMetadata);
                    }
                }
                System.out.println("----------------------------------------------------------------------------------------");
                // Thread.sleep(1000);
            }
        }

        /*
        while (true) {
            ConsumerRecords<byte[], byte[]> records = consumer.poll(10);
            Iterator<ConsumerRecord<byte[], byte[]>> iterator = records.iterator();
            Map<String, Integer> map = new HashMap<String, Integer>();

            while (iterator.hasNext()) {
                ConsumerRecord<byte[], byte[]> record = iterator.next();

                if (record == null)
                    continue;

                BaseKey baseKey = GroupMetadataManager.readMessageKey(ByteBuffer.wrap(record.key()));
                byte[] value = record.value();
                if (value == null) {
                    continue;
                }
                System.out.println("value: " + new String(value, "UTF-8"));

                OffsetAndMetadata offset = GroupMetadataManager.readOffsetMessageValue(ByteBuffer.wrap(value));
                if (baseKey instanceof OffsetKey) {
                    OffsetKey newKey = (OffsetKey) baseKey;
                    String group = newKey.key().group();
                    TopicPartition tp = newKey.key().topicPartition();
                    System.out.println(group + "," + tp.topic() + "," + tp.partition() + "," + offset.offsetMetadata().offset());
                }
                Thread.sleep(1000);
            }
        }
        */
    }

    public static Consumer<byte[], byte[]> CreateKafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", Brokers);
        props.put("group.id", GroupID);
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        return new KafkaConsumer<byte[], byte[]>(props);
    }
}
