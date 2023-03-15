import { Book } from "./book";

export class Shelf {
    id : number = 0;
    name : string = "Shelf";
    storage : number = 0;
    books : Book[] = [];
    libraryId : number = 0;
    libraryName : string = "libraryName";
    libraryCapacity : number = 0;
    libraryCurrentCapacity : number =0;
  } 