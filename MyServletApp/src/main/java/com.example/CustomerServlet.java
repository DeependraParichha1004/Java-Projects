package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/customers/*")
public class CustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String path_info = req.getPathInfo();
        if(path_info==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Url");
            return;
        } else if ("/fetch".equals(path_info)) {
            fetch(req, resp);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String path_info = req.getPathInfo();
        System.out.println("POST path: " + path_info);
        if(path_info==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Url");
            return;
        }
        else if ("/registrations/add".equals(path_info)) {
            data_add(req, resp);
        }
        else if("/registrations/sign_in".equals(path_info)){
            validate_sign_in(req, resp);
        }
    }

    public void validate_sign_in(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String s1 = "Select password from registrations where email = ?";
        try(Connection con = DBUtil.getConnection();
            PreparedStatement st = con.prepareStatement(s1);
        ) {
            st.setString(1,email);
            ResultSet result = st.executeQuery();
            String pass_value = "";
            System.out.println("name: "+name);
            System.out.println("email: "+email);

            while (result.next()) {
                pass_value = result.getString("password");
            }
            System.out.println("password: "+pass_value);

            if (pass_value.equals(name)){
                resp.getWriter().write("User Signed Successfully");
            }
            else{
                resp.getWriter().write("username or password is incorrect!");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void data_add(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // deserialize
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = mapper.readValue(req.getInputStream(), Map.class);
        String name = data.get("name");
        String email = data.get("email");

        String s1 = "Insert into registrations (name, email) values (?,?)";
        System.out.println("name: "+name);
        System.out.println("email: "+email);
        try(Connection con = DBUtil.getConnection();
            PreparedStatement s = con.prepareStatement(s1);
        ){
            s.setString(1,name);
            s.setString(2,email);
            int rows = s.executeUpdate();

            resp.getWriter().write("User Registered Successfully");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void fetch(HttpServletRequest res, HttpServletResponse resp){
        String query = "Select * from customers";
        List<Map<String, Object>> users = new ArrayList<>();
        try(Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);
        ){
            while(rst.next()){
                Map<String, Object> row = new HashMap<>();
                row.put("name", rst.getString("cust_name"));
                row.put("city", rst.getString("city"));
                row.put("grade", rst.getString("grade"));
                users.add(row);
                ObjectMapper objectMapper =new ObjectMapper();
                String json = objectMapper.writeValueAsString(users);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
