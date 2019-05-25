package com.services.TrainingService;

import java.util.Objects;

public class WordsTrainingResult {
    private String value;
    private int time;

    public WordsTrainingResult(String value, int time) {
        this.value = value;
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WordsTrainingResult{" +
                "value='" + value + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordsTrainingResult that = (WordsTrainingResult) o;
        return time == that.time &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, time);
    }


}
