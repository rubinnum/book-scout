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
        if (currentIndex === 0) {
            booksDisplayed.current = await backend_api.get(`/progress/${subject}`).then(response => response.data.booksDisplayed);
        }
        const currentlyDisplayedBooks = booksDisplayed.current + (currentIndex + 1);
        console.log("will be sent - " + currentlyDisplayedBooks);
        await backend_api.put(`/progress/${subject}?booksDisplayed=${currentlyDisplayedBooks}`);
    }

    const fetchBooks = async () => {
        await getBooksBySubject(subject);
        console.log("It goes from first");
        await updateNumberOfDisplayedBooks(subject);
    }

    // Effect for initial load (only once)
    useEffect(() => {
        if (!booksInitialized.current || (currentIndex === books.length && books.length !== 0)) {
            console.log("I am here now");
            booksInitialized.current = true;

            fetchBooks();
        }

    }, [subject, currentIndex]);

    // Effect for fetching new batch of books
    useEffect(() => {
        if (currentIndex !== 0) {
            console.log("It goes from second");
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