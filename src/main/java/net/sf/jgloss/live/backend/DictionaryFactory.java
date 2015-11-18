/*
 * Copyright (C) 2002-2015 Michael Koch (tensberg@gmx.net)
 *
 * This file is part of JGloss.
 *
 * JGloss is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * JGloss is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JGloss; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package net.sf.jgloss.live.backend;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import jgloss.dictionary.filebased.EDict;

@Component
public class DictionaryFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryLookupService.class);

    @Value("${edictPath}")
    private String edictPath;

    @Bean
    public Dictionaries loadDictionaries() {
        try {
            EDict edict = new EDict(new File(edictPath), "EUC-JP");
            edict.loadIndex();
            return new Dictionaries(Collections.singletonList(edict));
        } catch (IOException e) {
            LOGGER.error("failed to load dictionary", e);
            throw new BeanCreationException("failed to load dictionary", e);
        }
    }
}
