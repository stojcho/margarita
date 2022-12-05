import { Swiper, SwiperSlide } from "swiper/react/swiper-react.js";
import { Navigation, Pagination } from "swiper";
import "swiper/swiper-bundle.min.css";
import "swiper/swiper.min.css";

import testimonial1 from "../../assets/images/temp/testimonial-1.jpg";
import testimonial2 from "../../assets/images/temp/testimonial-2.jpg";
import testimonial3 from "../../assets/images/temp/testimonial-3.jpg";

export default function SectionTestimonials() {
  return (
    <section className="section-testimonials">
      <header className="section__head" data-aos="fade-up">
        <div className="shell">
          <h5>Opinions</h5>

          <h2>What Our Customers Say</h2>
        </div>
      </header>

      <div className="section__content">
        <div className="shell">
          <div
            className="slider-testimonials"
            data-aos="zoom-in-up"
            data-aos-offset="200"
          >
            <Swiper
              className="slider__clip"
              modules={[Navigation, Pagination]}
              allowTouchMove={false}
              loop={true}
              slidesPerView={"auto"}
              speed={1000}
              pagination={{
                el: ".swiper-pagination",
                type: "bullets",
                clickable: true,
              }}
              navigation={{
                nextEl: ".slider__next",
                prevEl: ".slider__prev",
              }}
              spaceBetween={50}
              centeredSlides={true}
              autoplay={{
                delay: 10000,
                disableOnInteraction: false,
              }}
              breakpoints={{
                0: {
                  spaceBetween: 0,
                },
                1024: {
                  spaceBetween: 30,
                },
                1200: {
                  spaceBetween: 50,
                },
              }}
            >
              <div className="slider__slides swiper-wrapper">
                <SwiperSlide className="slider__slide">
                  <div className="testimonial">
                    <div className="testimonial__image">
                      <img src={testimonial1} alt="" />
                    </div>

                    <div className="testimonial__content">
                      <div className="testimonial__entry">
                        <blockquote>
                          <cite>Samira Khan</cite>

                          <p>
                            Lorem ipsum dolor sit amet, consectetur adipisicing
                            elit. Hic officia natus omnis minus, debitis
                            praesentium fugit perferendis porro repellendus
                            facere quasi provident eveniet officiis perspiciatis
                            autem soluta, obcaecati culpa dolorem.
                          </p>
                        </blockquote>
                      </div>

                      <div className="testimonial__rating">
                        <div className="stars">
                          <ul>
                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </SwiperSlide>

                <SwiperSlide className="slider__slide">
                  <div className="testimonial">
                    <div className="testimonial__image">
                      <img src={testimonial2} alt="" />
                    </div>

                    <div className="testimonial__content">
                      <div className="testimonial__entry">
                        <blockquote>
                          <cite>Samira Khan</cite>

                          <p>
                            Lorem ipsum dolor sit amet, consectetur adipisicing
                            elit. Hic officia natus omnis minus, debitis
                            praesentium fugit perferendis porro repellendus
                            facere quasi provident eveniet officiis perspiciatis
                            autem soluta, obcaecati culpa dolorem.
                          </p>
                        </blockquote>
                      </div>

                      <div className="testimonial__rating">
                        <div className="stars">
                          <ul>
                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </SwiperSlide>

                <SwiperSlide className="slider__slide">
                  <div className="testimonial">
                    <div className="testimonial__image">
                      <img src={testimonial3} alt="" />
                    </div>

                    <div className="testimonial__content">
                      <div className="testimonial__entry">
                        <blockquote>
                          <cite>Samira Khan</cite>

                          <p>
                            Lorem ipsum dolor sit amet, consectetur adipisicing
                            elit. Hic officia natus omnis minus, debitis
                            praesentium fugit perferendis porro repellendus
                            facere quasi provident eveniet officiis perspiciatis
                            autem soluta, obcaecati culpa dolorem.
                          </p>
                        </blockquote>
                      </div>

                      <div className="testimonial__rating">
                        <div className="stars">
                          <ul>
                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>

                            <li>
                              <i className="fas fa-star"></i>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </SwiperSlide>
              </div>

              <div className="slider__actions">
                <div className="slider__prev">
                  <i className="fas fa-chevron-left"></i>
                </div>

                <div className="slider__next">
                  <i className="fas fa-chevron-right"></i>
                </div>
              </div>

              <div className="swiper-pagination"></div>
            </Swiper>
          </div>
        </div>
      </div>
    </section>
  );
}
