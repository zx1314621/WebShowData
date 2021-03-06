package edu.scu.xyl.dao;

import edu.scu.xyl.domain.VideoAccessTopN;
import edu.scu.xyl.utils.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoAccessTopNDAO {


    static Map<String,String> courses = new HashMap<String,String>();
    static {
        courses.put("4000", "MySQL优化");
        courses.put("4500", "Crontab");
        courses.put("4600", "Swift");
        courses.put("14540", "SpringData");
        courses.put("14704", "R");
        courses.put("14390", "机器学习");
        courses.put("14322", "redis");
        courses.put("14390", "神经网络");
        courses.put("14623", "Docker");
    }


    public String getCourseName(String id) {
        return courses.get(id);
    }
    /**
     *
     * query top 5 course in some day
     * @param day
     * @return
     */
    public List<VideoAccessTopN> query(String day) {
        List<VideoAccessTopN> res = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = MySQLUtils.getConnection();

            String sql = "select cms_id, times from day_video_access_topn_stat where day = ? order by times desc limit 5";

            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, day);

            rs = pstmt.executeQuery();

            VideoAccessTopN domain = null;
            while (rs.next()) {
                domain = new VideoAccessTopN();
                domain.setName(getCourseName(rs.getLong("cms_id")+""));
                domain.setValue(rs.getLong("times") + "");
                res.add(domain);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.release(connection, pstmt, rs);
        }

        return res;
    }

    public static void main(String[] args) {
        VideoAccessTopNDAO dao = new VideoAccessTopNDAO();

        for (VideoAccessTopN res : dao.query("20170511")) {
            System.out.println(res.getName() + ":" + res.getValue());
        }
    }

}
