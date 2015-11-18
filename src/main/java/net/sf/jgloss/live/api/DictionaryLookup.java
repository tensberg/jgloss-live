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

package net.sf.jgloss.live.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jgloss.live.backend.DictionaryLookupService;
import net.sf.jgloss.live.backend.LookupResult;

/**
 * Endpoint for dictionary lookups.
 */
@RestController
public class DictionaryLookup {
    private final DictionaryLookupService lookupService;

    @Autowired
    DictionaryLookup(DictionaryLookupService lookupService) {
        this.lookupService = lookupService;
    }

    @RequestMapping("/lookup/{text}")
    public LookupResult lookup(@PathVariable String text) {
        return lookupService.lookup(text);
    }
}
