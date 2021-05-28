export class Blog {
  id: string = "";
  title: string = "";
  content: string = "";
  creationDate: number = 0;
  lastUpdated: number = 0;
  countOfWords: Map<string, number> = new Map<string, number>();
}
