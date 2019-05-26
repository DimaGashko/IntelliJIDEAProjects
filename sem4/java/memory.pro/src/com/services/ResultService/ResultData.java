package com.services.ResultService;

import java.util.Objects;

public class ResultData {

    private String value;
    private String answer;
    private int time;
    private int index;

    public boolean isCorrect() {
        return answer.equalsIgnoreCase(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultData that = (ResultData) o;
        return time == that.time &&
                Objects.equals(value, that.value) &&
                Objects.equals(answer, that.answer);
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, answer, time);
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "value='" + value + '\'' +
                ", answer='" + answer + '\'' +
                ", time=" + time +
                '}';
    }
}
