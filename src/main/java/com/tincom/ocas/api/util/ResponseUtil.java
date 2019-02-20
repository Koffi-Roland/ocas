/**
 *
 * @author roland  (krolandaziawor@gmail.com)
 */
package com.tincom.ocas.api.util;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    public static <X extends Object> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders alert) {
       
//        ResponseEntity.ok(maybeResponse.get())
        return ResponseEntity.ok(maybeResponse.get()).notFound()
                .headers(alert)
                .build();
                
                
//        
    }

    public static <X extends Object> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
        return ResponseEntity.ok(maybeResponse.get());

    }
//    private HttpHeaders httpHeaders;
//    private ResponseEntity<Object> msg;

    public ResponseUtil() {

    }

}
