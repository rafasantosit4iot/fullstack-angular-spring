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

interface BookResponseItem {
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