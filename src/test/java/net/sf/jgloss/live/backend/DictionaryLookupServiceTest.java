package net.sf.jgloss.live.backend;

import static com.google.common.collect.Iterators.singletonIterator;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import jgloss.dictionary.Dictionary;
import jgloss.dictionary.DictionaryEntry;
import jgloss.dictionary.SearchMode;

@RunWith(MockitoJUnitRunner.class)
public class DictionaryLookupServiceTest {
    @Mock
    private Dictionary dictionary;

    @Mock
    private DictionaryEntry entry;

    private DictionaryLookupService service;

    @Before
    public void initMocks() {
        service = new DictionaryLookupService(new Dictionaries(singletonList(dictionary)));
        when(dictionary.getName()).thenReturn("fooDictionary");
        when(entry.getWord(0)).thenReturn("fooWord");
        when(entry.getReading(0)).thenReturn("fooReading");
        when(entry.getTranslationRomCount()).thenReturn(1);
        when(entry.getTranslationCrmCount(0)).thenReturn(2);
        when(entry.getTranslationSynonymCount(0, 0)).thenReturn(1);
        when(entry.getTranslationSynonymCount(0, 1)).thenReturn(2);
        when(entry.getTranslation(0, 0, 0)).thenReturn("foo");
        when(entry.getTranslation(0, 1, 0)).thenReturn("bar");
        when(entry.getTranslation(0, 1, 1)).thenReturn("baz");
        when(dictionary.search(notNull(SearchMode.class), notNull(Object[].class))).thenReturn(singletonIterator(entry));
    }

    @Test
    public void testLookup() {
        LookupResult result = service.lookup("foo");

        List<DictionaryResult> dictionaryResults = result.getDictionaryResults();
        assertThat(dictionaryResults).hasSize(1);
        DictionaryResult dictionaryResult = dictionaryResults.get(0);
        assertThat(dictionaryResult.getDictionaryName()).isEqualTo("fooDictionary");
        List<DictionaryResultEntry> entries = dictionaryResult.getDictionaryEntries();
        assertThat(entries).hasSize(1);
        DictionaryResultEntry actualEntry = entries.get(0);
        assertThat(actualEntry.getWord()).isEqualTo("fooWord");
        assertThat(actualEntry.getReading()).isEqualTo("fooReading");
        assertThat(actualEntry.getTranslations()).containsExactly("foo", "bar", "baz");
    }
}
