import React, {useEffect, useRef, useState} from 'react';
import {useOutletContext, useParams} from "react-router-dom";
import backend_api from "../api/backend_api.js";
import BookCarousel from "../components/BookCarousel/BookCarousel.jsx";
import CustomSpinner from "../components/elements/CustomSpinner.jsx";

function CategoryBooks() {
    const {subject} = useParams();
    const {books, setBooks} = useOutletContext();
    const [currentIndex, setCurrentIndex] = useState(0);
    const booksDisplayed = useRef(0);

    const [loading, setLoading] = useState(true);
    const booksInitialized = useRef(false);

    const getBooksBySubject = async (subject) => {
        const response = await backend_api.get(`/books/${subject}`);
        const loadedBooks = response.data.books;

        setBooks(loadedBooks);
        setCurrentIndex(0);
        setLoading(false);
    }

    const updateNumberOfDisplayedBooks = async (subject) => {
        if (!booksInitialized.current || currentIndex === books.length) {
            booksDisplayed.current = await backend_api.get(`/progress/${subject}`).then(response => response.data.booksDisplayed);
        }
        const currentlyDisplayedBooks = (currentIndex === books.length) ? (booksDisplayed.current + 1) : (booksDisplayed.current + (currentIndex + 1));
        await backend_api.put(`/progress/${subject}?booksDisplayed=${currentlyDisplayedBooks}`);
    }

    const fetchBooks = async () => {
        await getBooksBySubject(subject);
        await updateNumberOfDisplayedBooks(subject);
        booksInitialized.current = true;
    }

    // Effect for fetching books
    useEffect(() => {
        if (!booksInitialized.current || (currentIndex === books.length && books.length !== 0)) {
            fetchBooks();
        }
    }, [subject, currentIndex]);

    // Effect for updating displayed books
    useEffect(() => {
        if (currentIndex !== 0 && currentIndex !== books.length) {
            updateNumberOfDisplayedBooks(subject);
        }
    }, [currentIndex]);

    if (loading) {
        return <CustomSpinner />
    }

    return <BookCarousel
        books={books}
        currentIndex={currentIndex}
        setCurrentIndex={setCurrentIndex}
        subject={subject}
        setLoading={setLoading}
    />;
}

export default CategoryBooks;