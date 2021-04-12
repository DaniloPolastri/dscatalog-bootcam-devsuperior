export type ProductsResponse = {
    content: Product[];
    totalPages: number;
}

export type Product = {
    id: number;
    name: string;
    description: string;
    price: number;
    img_URL: string;
    date: string;
    category: Category[];
}

export type Category = {
    id: number;
    name: string;
}