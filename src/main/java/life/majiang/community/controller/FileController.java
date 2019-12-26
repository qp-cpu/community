package life.majiang.community.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.ApiOperation;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @PostMapping("/file/upload")
    @ApiOperation("markdown文件上传")
    public @ResponseBody Map<String,Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) throws IOException, MyException {
        Map<String,Object> resultMap = new HashMap<String,Object>();
//        String ext_Name = file.getOriginalFilename().split(".")[1];
        //保存
        try {
            File imageFolder= new File(request.getServletContext().getRealPath("img/upload"));
            File targetFile = new File(imageFolder,file.getOriginalFilename());
            InputStream input=null;
            input =new FileInputStream(targetFile);
            byte b[] = new byte[1024] ;
            int len = input.read(b) ;
            StorePath storePath = fastFileStorageClient.uploadFile(input, len, "png", null);
            String url="http://121.41.85.42/"+storePath.getFullPath();
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url",url);
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        System.out.println(resultMap.get("success"));
        return resultMap;
    }

}

