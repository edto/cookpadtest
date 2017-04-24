SET foreign_key_checks = 0;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS Menu;

CREATE TABLE Recipe (
  RecipeID INTEGER,
  Ingredients VARCHAR(100),
  Instructions VARCHAR(1000),
  PRIMARY KEY (RecipeID)
  );

CREATE TABLE Menu (
  MenuID INTEGER,
  Recipe VARCHAR(100),
  RecipeID INTEGER,
  PRIMARY KEY (MENUID),
  FOREIGN KEY (RecipeID) references Recipe(RecipeID)
 );

 /* IN RECIPE:
  * ---------------
  * RecipeID identifies the recipe in Menu
  * Many ingredients can compose a recipe
  * Many instructions which can be written in 1000 char steps compose a recipe
  *
  * IN MENU:
  * ---------------
  * MenuID identifies the menu
  * Recipe is the name is the recipe.
  * RecipeID identifies the Recipe and is a foreign key to the ID in the Recipe table.
  */