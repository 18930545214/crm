package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.*;
import com.hwua.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Users)表控制层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@RestController
@RequestMapping("user")
public class UsersController {
    /**
     * 服务对象
     */
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private LuceneProService luceneProService;
    Map<String,Object> map = new HashMap<>();

    /**
     * 通过主键查询单条数据
     *
     * @param username 用户名 password 密码
     * @return 单条数据
     */
    @GetMapping("/login")
    public ModelAndView login(String username,String password) throws Exception{
        System.out.println("登录中");
        String info=null;
        ModelAndView mv = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();//创建一个用户（主题）
        //判断当前用户是否登录成功
        if(!currentUser.isAuthenticated()){
            //把用户名和密码封装在UsernamePasswordToken中
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                //进行登录验证
                currentUser.login(token);
                //底层交给securityManager对象去调用注册得realm从文件或数据库中找到此登录用户的用户名和密码信息，拿到这些信息以后
                //和token中的用户名、密码进行比对。
            } catch (UnknownAccountException uae) {
                info="不存在此用户";
            } catch (IncorrectCredentialsException ice) {
                info="密码不正确";
            } catch (LockedAccountException lae) {
                info="账号锁定";
            } catch (AuthenticationException ae) {
                info=ae.getMessage();
            }
        }
        if(info==null){
            mv.setViewName("main");
            List<Product> products = productService.queryAll();
            luceneProService.createIndex(products);
        }else{
            mv.setViewName("login");
            mv.addObject("info",info);
        }
        System.out.println(info);
        return mv;
    }
    @GetMapping("/queryAll")
    public PageInfo<Users> queryAll(Integer pageNo,Integer pageSize) throws Exception{
        PageHelper.startPage(pageNo, pageSize);
        List<Users> users = usersService.queryAll();
        PageInfo<Users> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @PostMapping("/addUser")
    public Map<String,Object> insert(Users users) throws Exception{
        Map<String,Object> map = new HashMap<>();
        users = usersService.addUser(users);
        if (users!=null){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
    @GetMapping("show/{id}")
    public ModelAndView show(@PathVariable("id") String id) {
        Users users = usersService.queryById(id);
        ModelAndView mv = new ModelAndView("user-show");
        mv.addObject("users",users);
        return mv;

    }
    @PostMapping("/addRole")
    public Map<String,Object> addRole(UsersRole usersRole) throws Exception{
        int i = usersService.addUsersRole(usersRole);
        if (i>0){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
}