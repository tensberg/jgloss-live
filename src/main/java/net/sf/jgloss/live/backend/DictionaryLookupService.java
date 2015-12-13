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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

import jgloss.dictionary.Dictionary;
import jgloss.dictionary.DictionaryEntry;
import jgloss.dictionary.ExpressionSearchModes;
import jgloss.dictionary.SearchException;
import jgloss.dictionary.SearchFieldSelection;

/**
 * Lookup of words in the configured dictionaries.
 */
@Service
public class DictionaryLookupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryLookupService.class);

    private static final SearchFieldSelection SEARCH_ALL = new SearchFieldSelection(true, true, true, false, false);

    private final List<Dictionary> dictionaries;

    @Autowired
    DictionaryLookupService(Dictionaries dictionaries) {
        this.dictionaries = dictionaries.getDictionaries();
    }

    /**
     * Search for the given text in all dictionaries. Substring search in words,
     * readings and translations.
     *
     * @return All results of the lookup.
     */
    public LookupResult lookup(String text) {
        ImmutableList.Builder<DictionaryResult> dictionaryResults = ImmutableList.builder();

        for (Dictionary dictionary : dictionaries) {
            ImmutableList.Builder<DictionaryResultEntry> resultEntries = ImmutableList.builder();

            Iterator<DictionaryEntry> entries = dictionary.search(ExpressionSearchModes.ANY, new Object[] { text, SEARCH_ALL });
            while (entries.hasNext()) {
                try {
                    DictionaryEntry entry = entries.next();
                    List<String> translations = new ArrayList<>();
                    for (int rom = 0; rom < entry.getTranslationRomCount(); rom++) {
                        for (int crm=0; crm < entry.getTranslationCrmCount(rom); crm++) {
                            for (int synonym=0; synonym<entry.getTranslationSynonymCount(rom, crm); synonym++) {
                                translations.add(entry.getTranslation(rom, crm, synonym));
                            }
                        }
                    }
                    resultEntries.add(new DictionaryResultEntry(entry.getWord(0), entry.getReading(0), translations));
                } catch (SearchException e) {
                    LOGGER.debug("failed to create dictionary entry", e);
                }
            }

            dictionaryResults.add(new DictionaryResult(dictionary.getName(), resultEntries.build()));
        }

        return new LookupResult(dictionaryResults.build());
    }
}
