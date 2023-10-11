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

public class HelperPostBarDaoImpl implements HelperPostBarDao {
    @Override
    public int insertHelpPostBar(HelperPostBar helperPostBar) {

        Connection conn = new ConnOpen().getConnection("helpform");
        try{
            Statement st = conn.createStatement();
            String sql = "insert into help_post_bar(`help_user_name`,`help_user_uuid`,`help_location`,`trapped_people_number`,`trapped_time`,`trapped_description`,`need_help_kind`" +
                    ",`contact_people`,`contact_phone_number`,`release_time`,`urgency_degree`,`is_get_help`) values ("+ helperPostBar.toStringWithoutId()+ ")";
            st.execute(sql);
            Log.v("PostBarCommit:","Success!");
            conn.close();
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public List<HelperPostBar> getHelperPostBarList() {
        HelperPostBar hpb;
        List<HelperPostBar> list = new ArrayList<HelperPostBar>();
        Connection conn = new ConnOpen().getConnection("helpform");
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id,help_user_name,help_user_uuid,help_location,trapped_people_number,trapped_time,trapped_description,need_help_kind,contact_people,contact_phone_number,release_time,urgency_degree,is_get_help from help_post_bar");
            while(rs.next()){
                hpb = new HelperPostBar(rs.getInt("id"),rs.getString("help_user_name"),rs.getString("help_user_uuid"),rs.getString("help_location"),rs.getInt("trapped_people_number"),rs.getString("trapped_time"),rs.getString("trapped_description"),rs.getString("need_help_kind"),rs.getString("contact_people"),rs.getString("contact_phone_number"),rs.getString("release_time"),rs.getString("urgency_degree"),rs.getBoolean("is_get_help"));
                list.add(hpb);
            }
            conn.close();
            return list;
        }catch(Exception e){
            Log.v("FATAL:","selectAllHelpPostBar");
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int insertHelpPostBarWithCoordinate(HelperPostBar helperPostBar,double latitude,double longitude) {
        Connection conn = new ConnOpen().getConnection("helpform");
        try{
            Statement st = conn.createStatement();
            String sql = "insert into help_post_bar(`help_user_name`,`help_user_uuid`,`help_location`,`trapped_people_number`,`trapped_time`,`trapped_description`,`need_help_kind`" +
                    ",`contact_people`,`contact_phone_number`,`release_time`,`urgency_degree`,`is_get_help`) values ("+ helperPostBar.toStringWithoutId()+ ")";
            st.execute(sql);
            Log.v("PostBarCommit:",sql);
            new CoorDaoImpl().insertCoordinate(new Coordinate(latitude,longitude,22));
            conn.close();
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<HelperPostBar> getHelperPostBarListByUuid(String uuid) {
        HelperPostBar hpb;
        List<HelperPostBar> list = new ArrayList<HelperPostBar>();
        String sql = "select id,help_user_name,help_user_uuid,help_location,trapped_people_number,trapped_time,trapped_description,need_help_kind,contact_people,contact_phone_number,release_time,urgency_degree,is_get_help from help_post_bar where help_user_uuid = \"" + uuid + "\"";
        Log.v("SQL:",sql);
        Connection conn = new ConnOpen().getConnection("helpform");
        //String sql = "select id,help_user_name,help_user_uuid,help_location,trapped_people_number,trapped_time,trapped_description,need_help_kind,contact_people,contact_phone_number,release_time,urgency_degree,is_get_help from help_post_bar where help_user_uuid = \"" + uuid + "\"";
        Log.v("SQL:",sql);
        try{
            Statement st = conn.createStatement();
            //String sql = "select id,help_user_name,help_user_uuid,help_location,trapped_people_number,trapped_time,trapped_description,need_help_kind,contact_people,contact_phone_number,release_time,urgency_degree,is_get_help from help_post_bar where help_user_uuid = \"" + uuid + "\"";
            Log.v("SQL:",sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                hpb = new HelperPostBar(rs.getInt("id"),rs.getString("help_user_name"),rs.getString("help_user_uuid"),rs.getString("help_location"),rs.getInt("trapped_people_number"),rs.getString("trapped_time"),rs.getString("trapped_description"),rs.getString("need_help_kind"),rs.getString("contact_people"),rs.getString("contact_phone_number"),rs.getString("release_time"),rs.getString("urgency_degree"),rs.getBoolean("is_get_help"));
                list.add(hpb);
            }
            conn.close();
            return list;
        }catch(Exception e){
            Log.v("FATAL:","selectAllHelpPostBar");
            e.printStackTrace();
            return null;
        }
    }
    public int modifyHelperPostBar(int id,HelperPostBar newHelperPostBar) {
        int flag = deleteHelperPostBar(id);
        if(flag == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public int deleteHelperPostBar(int id) {
        Connection conn = new ConnOpen().getConnection("helpform");
        String sql = "DELETE FROM help_post_bar WHERE `id` = \"" + String.valueOf(id) + "\"";
        try {
            Statement st = conn.createStatement();
            st.execute(sql);
            conn.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
