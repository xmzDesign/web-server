package com.xmz.core.util;


import static com.xmz.core.constant.Context.DEFAULT_CONTENT_TYPE;

import eu.medsea.mimeutil.MimeUtil;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by SinjinSong on 2017/7/20.
 */
@Slf4j
public class MimeTypeUtil {
    static {
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
    }

    public static String getTypes(String fileName) {
        if(fileName.endsWith(".html")){
            return DEFAULT_CONTENT_TYPE;
        }
        Collection mimeTypes = MimeUtil.getMimeTypes(MimeTypeUtil.class.getResource(fileName));
        return mimeTypes.toArray()[0].toString();
    }

}
