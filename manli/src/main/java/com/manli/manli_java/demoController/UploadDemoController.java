package com.manli.manli_java.demoController;

import com.manli.manli_java.util.ResultBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/demo/upload")
public class UploadDemoController {
    @RequestMapping(value = "upload1", method = RequestMethod.GET)
    public ModelAndView upload() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("upload");
        return mv;
    }

    @RequestMapping(value = "upload2")
    public ResultBean postUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(
                                file.getOriginalFilename())));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("文件是空的");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return new ResultBean(0, "123", map);
    }


    @RequestMapping(value = "upload3", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean upload3(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        String name = params.getParameter("name");
        System.out.println("name:" + name);
        String id = params.getParameter("id");
        System.out.println("id:" + id);
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                }
            } else {
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return new ResultBean(0, "123", map);
    }
}
