import { ApiResponseBase } from "../../../shared/models/response";
import { BookSummaryItem } from "../../book/models/book";

export interface GenreSummaryItem {
    id: number;
    name: string;
}

export interface GenreResponse extends ApiResponseBase{
    content: GenreResponseItem[];
}

export interface GenreResponseItem {
    id: number;
    name: string;
    code: string;
    books: BookSummaryItem[];
}

export interface GenreCreateBody {
    name: string;
    code: string;
}

