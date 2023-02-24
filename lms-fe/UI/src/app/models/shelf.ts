import { Book } from "./book";
import { Category } from "./Category";

export class Shelf {
    id : number = 0;
    name : string = "Shelf";
    storage : number = 0;
    books : Book[] = [];
    libraryId : number = 0;
    libraryName : string = "libraryName";
    category!: Category;
  } 