package com.dn.springboot03.controller;

import com.dn.springboot03.dao.impl.ProblemDao;
import com.dn.springboot03.entity.Perssion;
import com.dn.springboot03.entity.Problem;
import com.dn.springboot03.entity.RoleVo;
import com.dn.springboot03.entity.User;
import com.dn.springboot03.service.IProblemSerice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    @Qualifier("problemService")
    IProblemSerice problemService2;

    @Autowired
    ProblemDao problemDao;

    @Value("${application.hello}")
    private String value;

    /**
     * 访问jsp
     * @param model
     * @return
     */
    @RequestMapping(value = {"/","/index"})
    public String index(Model model){
        System.out.println("index");
        model.addAttribute("value",value);
        return "index";
    }

    /**
     * 到登陆页面
     * @return
     */
    @RequestMapping("/gotoLogin")
    public String gotoLogin(){
        return "login";
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password,
                            HttpSession session) {
        logger.info("登陆的用户名{}和密码{}",username,password);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //在这里掉到了shiro中进行登录校验,如果权限校验没有通过就不会往下走
            subject.login(usernamePasswordToken); //完成登录
            User user = (User)subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        }
        catch (Exception e) {
            return "login";//返回登录页面
        }
    }

    /**
     * 获取权限
     * @param request
     * @return
     */
    @RequestMapping("/getPerssion")
    @ResponseBody
    public List<String> getPerssion(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Set<RoleVo> roleVos = user.getRoleVos();
        List<String> codeList = new ArrayList<>();
        if(roleVos != null && !roleVos.isEmpty()){
            Iterator<RoleVo> iterator = roleVos.iterator();
            while (iterator.hasNext()){
                RoleVo roleVo = iterator.next();
                Set<Perssion> perssions = roleVo.getPerssionSet();
                if(perssions != null && !perssions.isEmpty()){
                    Iterator<Perssion> it = perssions.iterator();
                    while (it.hasNext()){
                        Perssion perssion = it.next();
                        codeList.add(perssion.getCode());
                    }
                }
            }
        }
        return codeList;
    }

    /**
     * 到主页面
     */
    @RequestMapping("/home")
    public String home(){
        return "index";
    }

    /**
     * 返回json
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(){
        User user = new User();
        user.setId("3");
        return user;
    }

    /**
     * 返回json
     * @return
     */
    @RequestMapping("/getProblem")
    @ResponseBody
    public Problem getProblem(){
        Problem problem = problemService2.getProblemById(1);
        //Problem problem = problemDao.getProblemById(3);
        return problem;
    }

    @RequestMapping("/uptProblem")
    @ResponseBody
    public String uptProblem(){
        String res = "";
        try {
            problemService2.uptProblem(1);
            //Problem problem = problemDao.getProblemById(3);
            res = "更新成功";
        } catch (Exception e) {
            e.printStackTrace();
            res = "更新失败:"+e.getMessage();
        }
        return res;
    }

}
