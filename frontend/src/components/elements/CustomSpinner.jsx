import React from 'react';
import {Container, Spinner} from "react-bootstrap";

function CustomSpinner() {
    return (
        <Container className="d-flex align-items-center justify-content-center min-vh-100">
            <Spinner animation="border" variant="light" />
        </Container>
    );
}

export default CustomSpinner;