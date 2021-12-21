export class Item {
  constructor(
    public title: string,
    public price: number,
    public category: { categoryName: string; id?: number },
    public id?: number
  ) {}
}
