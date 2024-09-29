import React from 'react';
import {Container, Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router-dom";
import { Home, Book } from 'lucide-react';
import "./Header.css"

function Header() {
    return (
        <Navbar expand="lg" bg="dark" variant="dark" className="custom-navbar">
            <Container>
                <Navbar.Brand as={Link} to="/" className="d-flex align-items-center">
                    <Book size={32} className="text-primary me-2" />
                    <span>Book Scout</span>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="ms-auto">
                        <Nav.Link as={Link} to="/" className="d-flex align-items-center">
                            <Home size={18} className="me-1" />
                            Home
                        </Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;