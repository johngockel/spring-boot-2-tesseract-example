package de.johngockel.examples.tess.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tess")
@Getter
@Setter
public class TesseractProperties {

    private String tempDirectory;
    private String dataPath;
    private String language;
}
