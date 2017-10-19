package jdk.proxy.personImpl;

import jdk.proxy.person.PersonBean;

public class PersonBeanImpl implements PersonBean {
    @FileAnnotation(value = "YCQ")
    String name;
    @FileAnnotation
    String gender;
    @FileAnnotation
    String interests;
    @FileAnnotation
    int rating;
    @FileAnnotation
    int ratingCount = 0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int getHotOrNotRating() {
        if (ratingCount == 0) {
            return 0;
        }
        return (rating / ratingCount);
    }

    @Override
    public void setHotOrNorRating(int rating) {
        this.rating += rating;
        ratingCount++;
    }
}
