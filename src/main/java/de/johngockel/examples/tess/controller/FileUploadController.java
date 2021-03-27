package de.johngockel.examples.tess.controller;

import de.johngockel.examples.tess.config.TesseractProperties;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final TesseractProperties tesseractProperties;

    public FileUploadController(TesseractProperties tesseractProperties) {
        this.tesseractProperties = tesseractProperties;
    }

    @PostMapping
    public RedirectView singleFileUpload(@RequestParam("file") MultipartFile multipartFile,
                                         RedirectAttributes redirectAttributes) throws IOException, TesseractException, URISyntaxException {
        if (multipartFile != null) {
            File file = new File(tesseractProperties.getTempDirectory(), multipartFile.getOriginalFilename());
            if(file.exists()) {
                file.delete();
            }

            if (file.createNewFile() && file.exists()) {
                multipartFile.transferTo(file);

                Tesseract tesseract = new Tesseract();
                tesseract.setDatapath(tesseractProperties.getDataPath());
                tesseract.setLanguage(tesseractProperties.getLanguage());
                String text = tesseract.doOCR(file);

                redirectAttributes.addFlashAttribute("text", text);
                return new RedirectView("result");
            }
            redirectAttributes.addFlashAttribute("text", "Temporary file could not be created.");
            return new RedirectView("error");
        }
        redirectAttributes.addFlashAttribute("text", "No File in request found.");
        return new RedirectView("error");
    }
}
