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

public class DictionaryResultEntry {
    private final String word;
    private final String reading;
    private final String translation;

    DictionaryResultEntry(String word, String reading, String translation) {
        this.word = word;
        this.reading = reading;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public String getReading() {
        return reading;
    }

    public String getTranslation() {
        return translation;
    }
}
