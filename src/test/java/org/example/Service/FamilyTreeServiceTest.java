package org.example.Service;

import org.example.Model.FamilyTree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FamilyTreeServiceTest {
    @Mock
    PersonService personService;

    @Mock
    FamilyTree ft;

    @InjectMocks
    FamilyTreeService fts;

    @Test
    public void testAddChild() {

    }
}
