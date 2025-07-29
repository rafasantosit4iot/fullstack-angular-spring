import { BookSummaryItem } from "../../book/models/book";
import { CountrySummaryItem } from "../../country/models/country";

export interface AuthorSummaryItem {
    authorId: string;
    name: string;
    countryName: string;
}

interface AuthorResponseItem {
    id: string;
    name: string;
    birthday: Date;
    dayOfDeath: Date | null;
    country: CountrySummaryItem;
    books: BookSummaryItem[];
}