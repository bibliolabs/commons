/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chillenious.common.util.convert.converter;

import com.chillenious.common.util.convert.IConverter;

import java.util.Locale;

/**
 * Converts from Object to Float.
 *
 * @author Eelco Hillenius
 * @author Jonathan Locke
 */
public class FloatConverter extends AbstractDecimalConverter<Float> {
    private static final long serialVersionUID = 1L;

    /**
     * The singleton instance for a float converter
     */
    public static final IConverter<Float> INSTANCE = new FloatConverter();

    /**
     * @see com.chillenious.common.util.convert.IConverter#convertToObject(String, java.util.Locale)
     */
    @Override
    public Float convertToObject(final String value, final Locale locale) {
        final Number number = parse(value, -Float.MAX_VALUE, Float.MAX_VALUE, locale);

        if (number == null) {
            return null;
        }

        return number.floatValue();
    }

    /**
     * @see AbstractConverter#getTargetType()
     */
    @Override
    protected Class<Float> getTargetType() {
        return Float.class;
    }
}
