package org.am.dba.ms.mystudents.model;

/**
 * Created by Beschi Antony D on 10/6/2016.
 */
public class Student {
    private String name;
    private String std;
    private String profileImg;

    public Student(String name, String std, String profileImg) {
        super();
        this.name = name;
        this.std = std;
        this.profileImg = profileImg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStd() {
        return std;
    }
    public void setStd(String std) {
        this.std = std;
    }
    public String getProfileImg() {
        return profileImg;
    }
    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

}
