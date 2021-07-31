package com.hzvh.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzvh.travel.domain.Category;
import com.hzvh.travel.service.CategoryService;
import com.hzvh.travel.service.impl.CategoryServiceimpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/Category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceimpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.调用service查询所有
        List<Category> cs = service.findAll();
        //2.序列化json返回
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);
        //writeValue(cs,response);
    }
}
