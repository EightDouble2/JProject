package com.johnny.jshop.business.tests;

import com.johnny.jshop.business.dto.params.ProfileParam;
import com.johnny.jshop.commons.utils.MapperUtils;
import org.junit.Test;

public class PrintJsonTests {

    @Test
    public void testProfileParam() throws Exception {
        System.out.println(MapperUtils.obj2json(new ProfileParam()));
    }

}
