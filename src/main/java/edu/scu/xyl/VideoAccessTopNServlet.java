package edu.scu.xyl;

import edu.scu.xyl.dao.VideoAccessTopNDAO;
import edu.scu.xyl.domain.VideoAccessTopN;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * popular top n course
 */
public class VideoAccessTopNServlet extends HttpServlet {

    private static VideoAccessTopNDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new VideoAccessTopNDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String day = req.getParameter("day");

       List<VideoAccessTopN> res =  dao.query(day);

       System.out.print("len :" + res.size());

        JSONArray json = JSONArray.fromObject(res);

        resp.setContentType("text/html;charset=utf-8");



        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
