import { ApiResponseBase } from "../../../shared/models/response";
import { BookSummaryItem } from "../../book/models/book";
import { CountrySummaryItem } from "../../country/models/country";

export interface AuthorSummaryItem {
    authorId: string;
    name: string;
    countryName: string;
}

export interface AuthorResponseItem {
    id: string;
    name: string;
    birthday: Date;
    dayOfDeath: Date | null;
    country: CountrySummaryItem;
    books: BookSummaryItem[];
}

export interface AuthorResponse extends ApiResponseBase {
    content: AuthorResponseItem[]
}

export interface AuthorCreateBody {
    name: string;
    birthday: string;
    dayOfDeath: string;
    countryId: number;
}