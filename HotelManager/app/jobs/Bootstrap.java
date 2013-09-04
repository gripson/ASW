package jobs;

import models.RoomManagerDaytime;
import models.RoomTypeDaytime;
import play.jobs.Every;
import play.jobs.Job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pinjie
 * Date: 13-9-1
 * Time: 上午4:38
 * To change this template use File | Settings | File Templates.
 */
@Every("1h")
//@On("0 0 0 * * ?")
public class Bootstrap extends Job {

    public void doJob() {
        createrminfo();
    }

    public static void createrminfo(){
        //选出所有的房型
        List<RoomTypeDaytime> roomTypeDaytimes= RoomTypeDaytime.findAll();
        //根据房型找出所有相关余房
        List<RoomManagerDaytime>  roomManagerDaytimes=new ArrayList<RoomManagerDaytime>();
        for(int i=0;i<roomTypeDaytimes.size();i++){
            roomManagerDaytimes = RoomManagerDaytime.find("Roomtypedaytime_id=", roomTypeDaytimes.get(i).id).fetch();
            if(roomManagerDaytimes.size()<7){
                Date date=new Date();
                date=new Date(date.getYear(),date.getMonth(), date.getDate());
                for (int time =1;time<=7;time++){
                    List<RoomManagerDaytime> roomManagerDaytimeList = RoomManagerDaytime.find("Roomtypedaytime_id=? and Date=?", roomTypeDaytimes.get(i).id, date).fetch();//寻找Roomtypedaytime_id和date对应的那一个余房管理项目，如果有多个则删除其他

                    if (roomManagerDaytimeList.isEmpty()){
                        RoomManagerDaytime roommdt=new RoomManagerDaytime();
                        roommdt.roomtypedaytime = roomTypeDaytimes.get(i);
                        roommdt.preroom = roomTypeDaytimes.get(i).preroom;
                        roommdt.roomnumber = roomTypeDaytimes.get(i).preroom;
                        roommdt.roomprice = roomTypeDaytimes.get(i).loveprice;
                        roommdt.roomtype = roomTypeDaytimes.get(i).roomtype;
                        roommdt.date = date;
                        roommdt.state = "开售";
                        roommdt.save();
                    }
                    else if (roomManagerDaytimeList.size()>1){
                        for (int j=1;j<roomManagerDaytimeList.size();j++)
                            roomManagerDaytimeList.get(j).delete();
                    }
                    Long myTime=(date.getTime()/1000)+60*60*24;
                    date.setTime(myTime * 1000);
                }
            }
        }
    }
}
