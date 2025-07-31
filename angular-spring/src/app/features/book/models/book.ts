import { ApiResponseBase } from "../../../shared/models/response";
import { AuthorSummaryItem } from "../../author/models/author";
import { BookCopySummaryItem } from "../../book-copy/models/book-copy";
import { GenreSummaryItem } from "../../genre/models/genre";
import { PublisherSummaryItem } from "../../publisher/models/publisher";

export interface BookSummaryItem {
    id: string;
    title: string;
    editionNumber: number;
    yearOfRelease: number;
    isbnCode: string;
    genreName: string;
}

export interface BookResponseItem {
    id: string;
    title: string;
    editionNumber: number;
    synopsis: string;
    isbnCode: string;
    yearOfRelease: number;
    genre: GenreSummaryItem;
    author: AuthorSummaryItem;
    publisher: PublisherSummaryItem;
    copies: BookCopySummaryItem[];
}

export interface BookPageResponse extends ApiResponseBase{
    content: BookResponseItem[];
}

export interface BookCreateBody {
    title: string;
    editionNumber: number;
    synopsis: string;
    isbnCode: string;
    yearOfRelease: number;
    authorId: string;
    publisherId: string;
    genreId: number;
}