import './BookCarousel.css'

import React, {useEffect} from 'react';
import {Button, Container} from "react-bootstrap";
import BookCard from "../BookCard/BookCard.jsx";
import {ChevronDown, ChevronUp} from 'lucide-react';
import backend_api from "../../api/backend_api.js";

function BookCarousel({books, currentIndex, setCurrentIndex, subject, batchesNumber}) {

    const updateDisplayedBooksProgress = async (subject) => {
        const booksDisplayed = (batchesNumber * 10) + (currentIndex + 1);
        await backend_api.put(`/progress/${subject}?booksDisplayed=${booksDisplayed}`);
    }

    useEffect(() => {
        updateDisplayedBooksProgress(subject);
    }, [currentIndex, subject]);

    const handleNext = () => {
        if (currentIndex < books.length - 1) {
            setCurrentIndex(currentIndex + 1);
        }
    };

    const handlePrevious = () => {
        if (currentIndex > 0) {
            setCurrentIndex(currentIndex - 1);
        }
    };

    return (
        <Container className="d-flex flex-column align-items-center justify-content-center">
            <Button variant="secondary" onClick={handlePrevious} className="mb-4 rounded-circle"
                    disabled={currentIndex === 0}>
                <ChevronUp size={24}/>
            </Button>
            <div className="book-carousel-container">
                <BookCard book={books[currentIndex]}/>
            </div>
            <Button variant="secondary" onClick={handleNext} className="mt-4 rounded-circle">
                <ChevronDown size={24}/>
            </Button>
        </Container>
    )
}

export default BookCarousel;