tailwind.config = {
    theme: {
        container: {
            center: true,
            padding: {
                DEFAULT: "1rem",
                sm: "1rem",
                lg: "4rem",
                xl: "4rem",
                "2xl": "8rem",
            },
        },
        extend: {
            colors: {
                primary: '#fcaf17',
                secondary: '#feecc7',
                textColor: "#11006F",
            },
            animation: {
                slide: "slide 0.5s ease-in-out",
                swipeUp: "swipeUp 0.5s linear",
                scaleUp: "scaleUp 0.2s linear"
            },
            keyframes: {
                slide: {
                    "0%": { width: "0", opacity: "0" },
                    "100%": { width: "100%", opacity: "1" },
                },
                swipeUp: {
                    "0%": { opacity: "0" },
                    "15%": { top: "80%", opacity: "0.2" },
                    "40%": { top: "70%", opacity: "0.4" },
                    "70%": { top: "60%", opacity: "0.6" },
                    "100%": { top: "50%", opacity: "1" },
                },
                scaleUp: {
                    "from": {transform: "scale(1)"},
                    "to": {transform: "scale(1.2)"}
                }
            },
        }
    }
}
