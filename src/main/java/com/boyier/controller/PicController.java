package com.boyier.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping
public class PicController {

    /**
     * 使用response输出图片流
     */
    @GetMapping("/getimg")
    public void getImage(HttpServletResponse resp,String pic) throws IOException, IOException {
        String img_path = "/home/kuang/WXIMG/"+pic.substring(0,pic.length()-3)+'.'+pic.substring(pic.length()-3);
        System.out.println(img_path);
        final InputStream in = new FileInputStream(new File(img_path));
        resp.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(in, resp.getOutputStream());
    }
}
