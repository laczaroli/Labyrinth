package jfx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.time.Duration;
import java.time.ZonedDateTime;

public class GameResult {
    private int roundCount;

    private String playerName;
    private boolean solved;
    private Duration duration;
    private ZonedDateTime date;

    public GameResult(String playerName,int roundCount, Duration duration, boolean solved) {
        this.playerName = playerName;
        this.roundCount = roundCount;
        this.duration = duration;
        this.solved = solved;
        this.date = ZonedDateTime.now();
    }
}
