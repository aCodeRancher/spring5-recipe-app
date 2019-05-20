package guru.springframework.controllers;
import guru.springframework.domain.Recipe;

import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.junit.Assert.assertEquals;

@ComponentScan("guru.springframework")
public class IndexControllerTest1 {

   @Autowired
  private MockMvc mockMvc;

   @InjectMocks
   private IndexController indexController;

   @Mock
   private RecipeService recipeService;


    @Before
   public void setUp() throws Exception{
       MockitoAnnotations.initMocks(this);
       mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
       Set<Recipe> recipes = new HashSet<>();
       Recipe recipe = new Recipe();
       recipe.setId(1L);
       Recipe recipe1 = new Recipe();
       recipe.setId(2L);
       recipes.add(recipe);
       recipes.add(recipe1);
       when(recipeService.getRecipes()).thenReturn(recipes);


   }
  @Test
    public void testController() throws Exception{
        mockMvc.perform(get("/")).andExpect(view().name("index"));
        verify(recipeService, times(1)).getRecipes();
        assertEquals(2, recipeService.getRecipes().size());

    }
}

