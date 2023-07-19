package com.example.bai_1.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FootballPlayerDto implements Validator {
    private int id;
    @NotBlank(message = "không được để trống")
    @Pattern(regexp = "^[\\p{Lu}][\\p{Ll}]{1,8}(\\s([\\p{Lu}]|[\\p{Lu}][\\p{Ll}]{1,10})){0,5}$",
            message = "không chứa ký tự đặt biệt,từ 5 đến 100 kí tự ")
    private String name;
    private String birthday;
    @Min(value = 1, message = "kinh nghiệm phải là số dương ")
    private int experience;
    private String position;
    private String image;
    private Team team;

    public FootballPlayerDto(int id, String name, String birthday, int experience, String position, String image, Team team) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.experience = experience;
        this.position = position;
        this.image = image;
        this.team = team;
    }

    public FootballPlayerDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        FootballPlayerDto footballPlayerDto = (FootballPlayerDto) target;
        String birthdayStr = footballPlayerDto.getBirthday();
        if (!birthdayStr.trim().isEmpty()) {
            try {
                LocalDate birthday = LocalDate.parse(birthdayStr);
                Period age = Period.between(birthday, LocalDate.now()); // Tính độ tuổi
                int years = age.getYears(); // Lấy số năm từ kết quả Period
                if (years <= 16 || 100 <= years) {
                    errors.rejectValue("birthday", "birthday", "Tuổi phải từ 16 - 100 ! ");
                }
            } catch (DateTimeParseException e) {
                errors.rejectValue("birthday", "birthday", "Ngày sinh không hợp lệ ! ");
            }
        }
        List<String> stringList = new ArrayList<>();
        stringList.add("trung vệ");
        stringList.add("hộ vệ");
        stringList.add("tiền vệ");
        stringList.add("tiền đạo");
        stringList.add("thủ môn");
        String position = footballPlayerDto.getPosition();
        if (!stringList.contains(position)) {
            errors.rejectValue("position", "position", " vị trí không đúng ! ");
        }
    }
}
