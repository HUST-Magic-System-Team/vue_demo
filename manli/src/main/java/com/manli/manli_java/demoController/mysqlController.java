//package com.manli.manli_java.controller.demo;
//
//import com.manli.manli_java.util.ResultBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping(value = "api/demo/mysql")
//public class mysqlController {
//
//    @Autowired
//    RoomService roomService;
//
//    @Autowired
//    StudentService studentService;
//
//    /**
//     * 查询所有的数据内容
//     * @return
//     */
//    @RequestMapping(value = "/findByName",method = RequestMethod.GET)
//    public ResultBean findByName(String name){
//        List<Student> students=studentService.findByName(name);
//        Map<String, Object> map = new HashMap<>();
//        map.put("students", students);
//        return new ResultBean(0,"success", map);
//    }
//
//
//    /**
//     * 查询的另外一种方式
//     * @param name
//     * @param sex
//     * @return
//     */
//    @RequestMapping(value = "/withNameAndSexQuery",method = RequestMethod.GET)
//    public ResultBean withNameAndSexQuery(String name, int sex){
//        List<Student> students=studentService.withNameAndSexQuery(name, sex);
//        Map<String, Object> map = new HashMap<>();
//        map.put("students", students);
//        return new ResultBean(0,"success", map);
//    }
//
//    /**
//     * 删除
//     * @param name
//     * @return
//     */
//    @RequestMapping(value = "/deleteByName",method = RequestMethod.GET)
//    public ResultBean deleteByName(String name){
//        int a=studentService.deleteByName(name);
//        Map<String, Object> map = new HashMap<>();
//        map.put("students", 123);
//        return new ResultBean(0,"success", map);
//    }
//
//    /**
//     * 增加一行数据
//     * @param name
//     * @param sex
//     * @param createDate
//     * @return
//     */
//    @RequestMapping(value = "/add",method = RequestMethod.GET)
//    public ResultBean add(String name, int sex, Date createDate){
//        int a=studentService.Add(name,sex,createDate);
//        Map<String, Object> map = new HashMap<>();
//        map.put("students", 123);
//        return new ResultBean(0,"success", map);
//    }
//
//    /**
//     * 修改一行数据
//     * @param id
//     * @param name
//     * @return
//     */
//    @RequestMapping(value = "/modify",method = RequestMethod.GET)
//    public int modify(int id,String name){
//        int a=studentService.modify(id,name);
//        return a;
//    }
//
//
//}
