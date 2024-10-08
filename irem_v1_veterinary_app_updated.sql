PGDMP         5                |         
   veterinary    15.6    15.6 7    9           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            :           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ;           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            <           1262    25729 
   veterinary    DATABASE        CREATE DATABASE veterinary WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE veterinary;
                postgres    false            �            1259    25731    animal    TABLE       CREATE TABLE public.animal (
    id bigint NOT NULL,
    breed character varying(255),
    color character varying(255),
    date_of_birth date,
    gender character varying(255),
    name character varying(255),
    species character varying(255),
    customer_id bigint
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    25730    animal_id_seq    SEQUENCE     v   CREATE SEQUENCE public.animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.animal_id_seq;
       public          postgres    false    215            =           0    0    animal_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.animal_id_seq OWNED BY public.animal.id;
          public          postgres    false    214            �            1259    25740    appointment    TABLE     �   CREATE TABLE public.appointment (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id bigint,
    doctor_id bigint
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    25739    appointment_id_seq    SEQUENCE     {   CREATE SEQUENCE public.appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.appointment_id_seq;
       public          postgres    false    217            >           0    0    appointment_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.appointment_id_seq OWNED BY public.appointment.id;
          public          postgres    false    216            �            1259    25747    available_date    TABLE     d   CREATE TABLE public.available_date (
    id bigint NOT NULL,
    date date,
    doctor_id bigint
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false            �            1259    25746    available_date_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.available_date_id_seq;
       public          postgres    false    219            ?           0    0    available_date_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.available_date_id_seq OWNED BY public.available_date.id;
          public          postgres    false    218            �            1259    25754    customer    TABLE     �   CREATE TABLE public.customer (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    mp_no character varying(255),
    name character varying(255)
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    25753    customer_id_seq    SEQUENCE     x   CREATE SEQUENCE public.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.customer_id_seq;
       public          postgres    false    221            @           0    0    customer_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;
          public          postgres    false    220            �            1259    25763    doctor    TABLE     �   CREATE TABLE public.doctor (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    mp_no character varying(255),
    name character varying(255)
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    25762    doctor_id_seq    SEQUENCE     v   CREATE SEQUENCE public.doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.doctor_id_seq;
       public          postgres    false    223            A           0    0    doctor_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.doctor_id_seq OWNED BY public.doctor.id;
          public          postgres    false    222            �            1259    25772    vaccine    TABLE     �   CREATE TABLE public.vaccine (
    id bigint NOT NULL,
    code character varying(255),
    name character varying(255),
    animal_id bigint,
    protection_finish_date date,
    protection_start_date date
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    25771    vaccine_id_seq    SEQUENCE     w   CREATE SEQUENCE public.vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.vaccine_id_seq;
       public          postgres    false    225            B           0    0    vaccine_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.vaccine_id_seq OWNED BY public.vaccine.id;
          public          postgres    false    224            ~           2604    25734 	   animal id    DEFAULT     f   ALTER TABLE ONLY public.animal ALTER COLUMN id SET DEFAULT nextval('public.animal_id_seq'::regclass);
 8   ALTER TABLE public.animal ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215                       2604    25743    appointment id    DEFAULT     p   ALTER TABLE ONLY public.appointment ALTER COLUMN id SET DEFAULT nextval('public.appointment_id_seq'::regclass);
 =   ALTER TABLE public.appointment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    25750    available_date id    DEFAULT     v   ALTER TABLE ONLY public.available_date ALTER COLUMN id SET DEFAULT nextval('public.available_date_id_seq'::regclass);
 @   ALTER TABLE public.available_date ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            �           2604    25757    customer id    DEFAULT     j   ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);
 :   ALTER TABLE public.customer ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    25766 	   doctor id    DEFAULT     f   ALTER TABLE ONLY public.doctor ALTER COLUMN id SET DEFAULT nextval('public.doctor_id_seq'::regclass);
 8   ALTER TABLE public.doctor ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    25775 
   vaccine id    DEFAULT     h   ALTER TABLE ONLY public.vaccine ALTER COLUMN id SET DEFAULT nextval('public.vaccine_id_seq'::regclass);
 9   ALTER TABLE public.vaccine ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    225    225            ,          0    25731    animal 
   TABLE DATA           e   COPY public.animal (id, breed, color, date_of_birth, gender, name, species, customer_id) FROM stdin;
    public          postgres    false    215   >       .          0    25740    appointment 
   TABLE DATA           Q   COPY public.appointment (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public          postgres    false    217   �>       0          0    25747    available_date 
   TABLE DATA           =   COPY public.available_date (id, date, doctor_id) FROM stdin;
    public          postgres    false    219   U?       2          0    25754    customer 
   TABLE DATA           H   COPY public.customer (id, address, city, mail, mp_no, name) FROM stdin;
    public          postgres    false    221   �?       4          0    25763    doctor 
   TABLE DATA           F   COPY public.doctor (id, address, city, mail, mp_no, name) FROM stdin;
    public          postgres    false    223   �@       6          0    25772    vaccine 
   TABLE DATA           k   COPY public.vaccine (id, code, name, animal_id, protection_finish_date, protection_start_date) FROM stdin;
    public          postgres    false    225   kA       C           0    0    animal_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.animal_id_seq', 9, true);
          public          postgres    false    214            D           0    0    appointment_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.appointment_id_seq', 15, true);
          public          postgres    false    216            E           0    0    available_date_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.available_date_id_seq', 10, true);
          public          postgres    false    218            F           0    0    customer_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.customer_id_seq', 28, true);
          public          postgres    false    220            G           0    0    doctor_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.doctor_id_seq', 19, true);
          public          postgres    false    222            H           0    0    vaccine_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.vaccine_id_seq', 10, true);
          public          postgres    false    224            �           2606    25738    animal animal_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    215            �           2606    25745    appointment appointment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    217            �           2606    25752 "   available_date available_date_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    219            �           2606    25761    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    221            �           2606    25770    doctor doctor_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    223            �           2606    25781 %   customer uk_723piddlk92jf0t11rfdmr7lg 
   CONSTRAINT     a   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_723piddlk92jf0t11rfdmr7lg UNIQUE (mp_no);
 O   ALTER TABLE ONLY public.customer DROP CONSTRAINT uk_723piddlk92jf0t11rfdmr7lg;
       public            postgres    false    221            �           2606    25812    customer uk_mail 
   CONSTRAINT     K   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_mail UNIQUE (mail);
 :   ALTER TABLE ONLY public.customer DROP CONSTRAINT uk_mail;
       public            postgres    false    221            �           2606    25810    customer uk_mpno 
   CONSTRAINT     L   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_mpno UNIQUE (mp_no);
 :   ALTER TABLE ONLY public.customer DROP CONSTRAINT uk_mpno;
       public            postgres    false    221            �           2606    25783 $   vaccine uk_ppunly98x0marp6e4glgtjn45 
   CONSTRAINT     _   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT uk_ppunly98x0marp6e4glgtjn45 UNIQUE (code);
 N   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT uk_ppunly98x0marp6e4glgtjn45;
       public            postgres    false    225            �           2606    25779    vaccine vaccine_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    225            �           2606    25789 '   appointment fk2kkeptdxfuextg5ch7xp3ytie    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie;
       public          postgres    false    3205    215    217            �           2606    25784 "   animal fk6pvxm5gfjqxclb651be9unswe    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 L   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk6pvxm5gfjqxclb651be9unswe;
       public          postgres    false    221    3211    215            �           2606    25799 *   available_date fkk0d6pu1wxarsoou0x2e1cc2on    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 T   ALTER TABLE ONLY public.available_date DROP CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on;
       public          postgres    false    3219    219    223            �           2606    25804 #   vaccine fkne3kmh8y5pcyxwl4u2w9prw6j    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j;
       public          postgres    false    3205    215    225            �           2606    25794 '   appointment fkoeb98n82eph1dx43v3y2bcmsl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl;
       public          postgres    false    223    3219    217            ,   �   x�]�An�0E��S��l'Q�(��"U�e7%��l�*�bê;8X�@��v���3� �k���uݹPk� �P��j�P��9��!x5�3rj�Ǎ��2	�N�m��d���x����/�V�[����?�9C���X�S;U�����6�m��~7�	{��)��B���P��U�q�~��`�����am
r��/����/P�(�ܰҶ�%�%*c�3��/�j>      .   K   x�U��	�@D��L6���<�������p'HYm��C�f�#�@�d��!�&�\��H�G��z�9��\G4      0   <   x�MǱ� �Z�;&�d�9����><�P�Ѫ��S}1��0ΪN󮸪y�u��,�      2   �   x�]�?n�0���9J�6�P�b�ХR�Wb��m,%f�p�N��BY:us�W�04������Ga��p����wq�vpu���G����B��T�+xߪ�yj�7i<`4��s�VR���!���;$���]��x��<S%�8�^���x"�C:cD����u8D��'4���)�x���K��'�o�-���_�Z����ъ|�1髷��nfM����r���𘧦
�[|]B� Z�T      4   �   x�m�1�0������H��H7%n��.�1T�@1�.&^A'7z0�	����sH����i�j��ƀ+j�s�9[��w}3Qf�M$��$�i,6[	CHu黪��L�uER�U�	G��n�KD�p!�HT@�������f�"*�DH�	�a��|gF�4ax�<�`�	;G��?��y�      6   �   x�U�K
�@��ur
/���Uq�"ZT܌e�`�R��ѩ��'|!DC��֮����
�P�D����?�2R3q3�J��%钌�Y+���?����"��Gz�`��*�O�K�����$�H�Wb��a>[)�3k��K~ps�~�$i���
ƛ��@X�(�f}�����p�Nq��|k|y|��O浔~�H�w�	_VE�     