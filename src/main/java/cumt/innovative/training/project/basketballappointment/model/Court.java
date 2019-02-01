package cumt.innovative.training.project.basketballappointment.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cumt.innovative.training.project.basketballappointment.logger.Logger;
import cumt.innovative.training.project.basketballappointment.utils.DateUtil;
import cumt.innovative.training.project.basketballappointment.utils.ExceptionUtil;
import cumt.innovative.training.project.basketballappointment.utils.annotation.AutoIncrement;
import cumt.innovative.training.project.basketballappointment.utils.annotation.ReadOnly;
import cumt.innovative.training.project.basketballappointment.utils.annotation.TableName;

@TableName("court")
public class Court {
    @AutoIncrement
    private int id;
    private String name;
    private double x;
    private double y;
    private String pics;
    private int courtNumber;

    @ReadOnly
    private String reserved;

    public Court() {
        id = -1;
        x = y = 0;
        name = reserved = "";
        pics = null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getPics() {
        return pics;
    }

    public void setCourtNumber(int courtNumber) {
        this.courtNumber = courtNumber;
    }

    public int getCourtNumber() {
        return courtNumber;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            Logger.log(new Throwable(), Logger.DANGER, ExceptionUtil.getSimpleMessage(e));
        }
        return null;
    }



}
