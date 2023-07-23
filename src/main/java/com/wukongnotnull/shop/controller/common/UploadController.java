package com.wukongnotnull.shop.controller.common;

import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.util.ShopUtil;
import com.wukongnotnull.shop.util.Result;
import com.wukongnotnull.shop.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 悟空非空也
 */
@Controller
@RequestMapping("/admin")
public class UploadController {

    @Resource
    private StandardServletMultipartResolver standardServletMultipartResolver;

    @PostMapping({"/upload/file"})
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest,
                         @RequestParam("file") MultipartFile file)
            throws URISyntaxException, IOException {
        String fileName = file.getOriginalFilename();
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        if (bufferedImage == null) {
            return ResultGenerator.genFailResult("请上传图片类型的文件");
        }
        // 获得文件名后缀
        if (fileName == null) {
            return ResultGenerator.genFailResult("请上传图片的文件名为空");
        }
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        // 指定目录下，创建 新目录实例
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);

        //创建文件
        File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            // transfer 转移文件数据
            file.transferTo(destFile);
            // 返回服务器端的绝对路径给前端
            String url = ShopUtil.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/goods-img/" + newFileName;
            System.out.println("绝对路径为：url = " + url);
            return ResultGenerator.genSuccessResult((Object) url);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }

    @PostMapping({"/upload/files"})
    @ResponseBody
    public Result uploadBatch(HttpServletRequest httpServletRequest)
            throws URISyntaxException, IOException {
        // MultipartFile 好比文件的接收员
        List<MultipartFile> multipartFiles = new ArrayList<>(8);

        if (standardServletMultipartResolver.isMultipart(httpServletRequest)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iter = multiRequest.getFileNames();
            int total = 0;
            while (iter.hasNext()) {
                if (total > 5) {
                    return ResultGenerator.genFailResult("最多上传5张图片");
                }
                total += 1;
                MultipartFile file = multiRequest.getFile(iter.next());
                BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
                // 只处理图片类型的文件
                if (bufferedImage != null) {
                    multipartFiles.add(file);
                }
            }
        }

        if (CollectionUtils.isEmpty(multipartFiles)) {
            return ResultGenerator.genFailResult("请选择图片类型的文件上传");
        }
        if (multipartFiles != null && multipartFiles.size() > 5) {
            return ResultGenerator.genFailResult("最多上传5张图片");
        }

        List<String> fileNames = new ArrayList(multipartFiles.size());
        for (int i = 0; i < multipartFiles.size(); i++) {
            String fileName = multipartFiles.get(i).getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //生成文件名称通用方法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random r = new Random();
            StringBuilder tempName = new StringBuilder();
            tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
            String newFileName = tempName.toString();
            File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
            //创建文件
            File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
            try {
                if (!fileDirectory.exists()) {
                    if (!fileDirectory.mkdir()) {
                        throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                    }
                }
                multipartFiles.get(i).transferTo(destFile);
                fileNames.add(ShopUtil.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/goods-img/" + newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultGenerator.genFailResult("文件上传失败");
            }
        }
        return ResultGenerator.genSuccessResult((Object) fileNames);

    }

}
