import './BookCarousel.css'

import React, {useState} from 'react';
import {Button, Container} from "react-bootstrap";
import BookCard from "../BookCard/BookCard.jsx";
import {ChevronDown, ChevronUp} from 'lucide-react';

function BookCarousel({books}) {
    const [currentIndex, setCurrentIndex] = useState(0);

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
        <Container className="d-flex flex-column align-items-center justify-content-center min-vh-100">
            <Button variant="secondary" onClick={handlePrevious} className="mb-4 rounded-circle" disabled={currentIndex === 0}>
                <ChevronUp size={24} />
            </Button>
            <div className="book-carousel-container">
                <BookCard book={books[currentIndex]} />
            </div>
            <Button variant="secondary" onClick={handleNext} className="mt-4 rounded-circle" disabled={currentIndex === books.length - 1}>
                <ChevronDown size={24} />
            </Button>
        </Container>
    )
}

export default BookCarousel;