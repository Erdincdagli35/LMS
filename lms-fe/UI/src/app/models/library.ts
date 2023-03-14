import { Shelf } from "../models/shelf";

export class Library {
    id : number = 0;
    name : string = "Library";
    capacity : number = 0;
    currentCapacity : number = 9999;
    shelves : Shelf[] = [];
  }