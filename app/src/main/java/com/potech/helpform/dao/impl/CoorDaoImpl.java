package com.potech.helpform.dao.impl;

import android.util.Log;

import com.potech.helpform.dao.HelperPostBarDao;
import com.potech.helpform.entity.Coordinate;
import com.potech.helpform.entity.HelperPostBar;
import com.potech.helpform.utils.ConnOpen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoorDaoImpl {


    public List<Coordinate> getCoordinateList() {
        Coordinate coor;
        List<Coordinate> list = new ArrayList<Coordinate>();
        Connection conn = new ConnOpen().getConnection("helpform");
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id,latitude,longitude,bar_id from coordinate");
            while(rs.next()){
                coor = new Coordinate(rs.getInt("id"),rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getInt("bar_id"));
                list.add(coor);
            }
            conn.close();
            return list;
        }catch(Exception e){
            Log.v("FATAL:","getCoordinateList");
            e.printStackTrace();
            return null;
        }
    }
    public int insertCoordinate(Coordinate c) {

        Connection conn = new ConnOpen().getConnection("helpform");
        try{
            Statement st = conn.createStatement();
            String sql = "insert into coordinate(`latitude`,`longitude`,`bar_id`) values ("+ c.toStringWithoutId()+ ")";
            st.execute(sql);
            Log.v("CoorDaoImpl:",sql);
            conn.close();
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }



}
